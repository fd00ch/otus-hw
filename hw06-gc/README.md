# Сборщик мусора. ДЗ 
Акимов Александр

before otimization:
optimal heap size is 1024mb

256: 	spend msec:13830, sec:13
		spend msec:13955, sec:13
		spend msec:13981, sec:13

512: 	spend msec:9078, sec:9
		spend msec:9197, sec:9
		spend msec:9083, sec:9

1024: 	spend msec:8291, sec:8
		spend msec:8322, sec:8
		spend msec:8270, sec:8

2048: 	spend msec:7879, sec:7
		spend msec:7967, sec:7
		spend msec:7903, sec:7
		
after otimization (remove unnecessary boxing for class Data and fields sum, prevValue, prevPrevValue, sumLastThreeValues, someValue):
optimal heap size is 512mb

256: 	spend msec:1798, sec:1
		spend msec:1723, sec:1
		spend msec:1810, sec:1
		
512: 	spend msec:1206, sec:1
		spend msec:1298, sec:1
		spend msec:1250, sec:1

1024: 	spend msec:1212, sec:1
		spend msec:1200, sec:1
		spend msec:1205, sec:1

2048: 	spend msec:1225, sec:1
		spend msec:1233, sec:1
		spend msec:1228, sec:1