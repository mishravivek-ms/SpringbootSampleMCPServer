package com.example.examplemcpserver.tools;

import org.springframework.ai.tool.annotation.Tool;
import java.time.LocalDate;

public class DateTool {

    @Tool(name = "addDays", description = "Adds days to the current date")
    public LocalDate addDays(int days) {
        return LocalDate.now().plusDays(days);
    }

    @Tool(name = "subtractDays", description = "Subtracts days from the current date")
    public LocalDate subtractDays(int days) {
        return LocalDate.now().minusDays(days);
    }
}
