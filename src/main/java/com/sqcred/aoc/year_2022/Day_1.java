/*
 * Copyright 2023 sqcred
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sqcred.aoc.year_2022;

import com.sqcred.aoc.Input;
import com.sqcred.aoc.Puzzle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day_1 implements Puzzle {

    @Override
    public String getResult(Input input, List<String> extraArgs) {
        List<String> lines = input.asList();

        Map<Integer, Integer> amount = new HashMap<>();

        int currentElf = 0;
        int currentAmount = 0;
        for (String line : lines) {
            if(line.isEmpty()){
                // new elf
                amount.put(currentElf, currentAmount);
                currentElf++;
                currentAmount = 0;
                continue;
            }
            currentAmount += Integer.parseInt(line);
        }

        /*int biggestIntElf = 0;
        int biggestInt = 0;

        for (Integer elf : amount.keySet()) {
            int amountInt = amount.get(elf);
            if(amountInt > biggestInt){
                biggestInt = amountInt;
                biggestIntElf = elf;
            }
        }*/

        //return "Elf " + biggestIntElf + " has the most calories with: " + biggestInt + " calories";

        int top3amount = 0;

        for(int i = 0; i < 3; i++){
            int biggestInt = 0;
            int biggestElf = 0;

            for (Integer elf : amount.keySet()) {
                int amountInt = amount.get(elf);
                if(amountInt > biggestInt){
                    biggestInt = amountInt;
                    biggestElf = elf;
                }
            }

            amount.remove(biggestElf);
            top3amount += biggestInt;
        }

        return "Top 3 calories combined are: " + top3amount;
    }

}
