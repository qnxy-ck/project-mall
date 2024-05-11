package com.ck.mall.frontend;

import com.ck.mall.frontend.command.DemoCommandE;
import com.ck.mall.frontend.console.ConsoleValueMapping.ValueMappings;
import com.ck.mall.frontend.console.PrintIndentLevel;

import java.time.LocalDate;

import static com.ck.mall.frontend.console.ConsoleTips.printCommandInfo;
import static com.ck.mall.frontend.console.ConsoleTips.tips;
import static com.ck.mall.frontend.console.ConsoleValueRead.nextValue;

/**
 * @author Qnxy
 */
public class ApplicationMain {

    public static void main(String[] args) {

        printCommandInfo(DemoCommandE.class, PrintIndentLevel.ZERO);

        LocalDate date = nextValue(tips("请输入日期"), ValueMappings::toLocalDate);
        System.out.println("date = " + date);

        System.out.println();

        printCommandInfo(DemoUserGenderE.class, PrintIndentLevel.ZERO);

        DemoUserGenderE userGenderE = nextValue(tips("请输入性别"), DemoUserGenderE.class);
        System.out.println("userGenderE = " + userGenderE);


    }

}
