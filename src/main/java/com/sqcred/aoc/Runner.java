/*
 * Copyright 2023 sqcred
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sqcred.aoc;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {

    @SneakyThrows
    public static void main(String[] args) {

        long startTime;

        int day = 1;
        int year = 2022;
        List<String> extraArgs = new ArrayList<>();

        Puzzle puzzle = null;

        // Arguments parsing
        if(args.length > 0){
            day = Integer.parseInt(args[0]);
            if(args.length > 1){
                year = Integer.parseInt(args[1]);
                if(args.length > 2){
                    extraArgs = new ArrayList<>(Arrays.stream(args).toList());
                    extraArgs.remove(0);
                    extraArgs.remove(1);
                }
            }
        }

        // Searching puzzle
        String packageName = Runner.class.getPackage().getName();
        for(Class<?> clazz : new Reflections(packageName + ".year_" + year).getSubTypesOf(Puzzle.class)){
            if(clazz.getSimpleName().equals("Day_" + day)){
                puzzle = (Puzzle) clazz.getDeclaredConstructor().newInstance();
            }
        }

        // null check ofcourse
        if(puzzle == null){
            System.out.println("Could not find puzzle for year " + year + ", day " + day + "!");
            return;
        }

        if(!Files.exists(Path.of(Runner.class.getClassLoader().getResource(year + File.separator + "day_" + day + ".txt").toURI()))){
            System.out.println("Could not find puzzle input for year " + year + ", day " + day + "!");
            return;
        }

        // lets generate the result
        System.out.println("Generating result for year " + year + ", day " + day + "...");
        startTime = System.currentTimeMillis();
        String result = puzzle.getResult(new Input(year + File.separator + "day_" + day + ".txt"), extraArgs);

        System.out.println("Result: " + result);
        System.out.println("Result generated in " + (System.currentTimeMillis() - startTime) + "ms");

    }

}
