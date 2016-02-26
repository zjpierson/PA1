#!/bin/bash

for (( i = 10; i <= 15; i++)) do
    for (( j = 1; j <= 12; j++)) do
        if [ $j -gt 9 ] 
        then
            eval wget http://www.mcs.sdsmt.edu/csc468/Assignments/PA1/data/20$i-$j.xml
        else
            eval wget http://www.mcs.sdsmt.edu/csc468/Assignments/PA1/data/20$i-0$j.xml
        fi
    done
done


