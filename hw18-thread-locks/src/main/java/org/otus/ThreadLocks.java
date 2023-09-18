package org.otus;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class ThreadLocks {
    public static void main(String[] args) {
        Printer printer = new Printer();
        int repeats = 1;
        Thread thread1 = new Thread(() -> printer.printNumbers(repeats, printer::firstThreadCriticalSection));
        Thread thread2 = new Thread(() -> printer.printNumbers(repeats, printer::secondThreadCriticalSection));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Printer {
        Lock lock = new ReentrantLock();
        Condition firstThreadPrinted = lock.newCondition();
        Condition secondThreadPrinted = lock.newCondition();
        private boolean firstThreadIsPrinting = true; // start with first thread

        private void firstThreadCriticalSection(int number) {
            lock.lock();
            try {
                if (!firstThreadIsPrinting) {
                    secondThreadPrinted.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + number + " ");
                firstThreadIsPrinting = false;
                firstThreadPrinted.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        private void secondThreadCriticalSection(int number) {
            lock.lock();
            try {
                if (firstThreadIsPrinting) {
                    firstThreadPrinted.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + number + " ");
                firstThreadIsPrinting = true;
                secondThreadPrinted.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printNumbers(int repeats, Consumer<Integer> criticalSection) {
            int number = 1;
            boolean increasing = true;
            boolean firstTopNumberIsReached = false;
            while (repeats > 0) {
                criticalSection.accept(number);
                if (number == 10) {
                    increasing = false;
                    firstTopNumberIsReached = true;
                } else if (number == 1) {
                    increasing = true;
                    if (firstTopNumberIsReached) {
                        repeats--;
                    }
                }
                if (increasing) {
                    number++;
                } else {
                    number--;
                }
            }
        }

    }
}