package com.jmu.onlinecourse.utils;

import com.jmu.onlinecourse.entity.TeachPlan;

/**
 * @author ywww
 * @date 2021-01-17 15:54
 */
public class PlansDataProviderUtil {
    public static int BEGIN_YEAR = 2020;

    public static String[] year_data = {
            "2020-2021年 ",
            "2021-2022年 ",
            "2022-2023年 ",
            "2023-2024年 ",
            "2024-2025年 ",
            "2025-2026年 ",
            "2026-2027年 ",
            "2027-2028年 ",
            "2028-2029年 ",
            "2029-2030年 "
    };

    public static String[] term_data = {
            "第一学期",
            "第二学期"
    };

    public static TeachPlan[] teachPlans_data = {
            new TeachPlan(2020,1,"09月01日-09月15日","毛泽东思想及其历史地位",4),
            new TeachPlan(2020,1,"09月16日-09月30日","新民主主义革命理论",4),
            new TeachPlan(2020,1,"10月08日-10月15日","社会主义改造理论",2),
            new TeachPlan(2020,1,"10月16日-10月23日","邓小平理论",2),
            new TeachPlan(2020,1,"10月24日-10月31日","三个代表”重要思想",2),
            new TeachPlan(2020,1,"11月01日-11月15日","科学发展观",4),
            new TeachPlan(2020,1,"11月16日-11月20日","习近平新时代中国特色社会主义思想及其历史地位",1),
            new TeachPlan(2020,1,"11月21日-11月25日","中国特色社会主义总任务",1),
            new TeachPlan(2020,1,"11月25日-11月30日","“五位一体”总体布局",1),
            new TeachPlan(2020,1,"12月01日-12月15日","全面推进国防和军队现代化",4),
            new TeachPlan(2020,1,"12月16日-12月31日","“一国两制”与祖国统一",4),
            new TeachPlan(2020,1,"01月01日-01月07日","全面从严治党",2),
            new TeachPlan(2020,2,"03月01日-03月15日","毛泽东思想及其历史地位",4),
            new TeachPlan(2020,2,"03月16日-03月31日","新民主主义革命理论",4),
            new TeachPlan(2020,2,"04月08日-04月15日","社会主义改造理论",2),
            new TeachPlan(2020,2,"04月16日-04月23日","社会主义建设道路初步探索的理论成果",2),
            new TeachPlan(2020,2,"04月24日-04月30日","三个代表”重要思想",2),
            new TeachPlan(2020,2,"05月01日-05月15日","科学发展观",4),
            new TeachPlan(2020,2,"05月16日-05月20日","习近平新时代中国特色社会主义思想及其历史地位",1),
            new TeachPlan(2020,2,"05月21日-05月25日","中国特色社会主义总任务",1),
            new TeachPlan(2020,2,"05月25日-05月31日","全面深化改革",1),
            new TeachPlan(2020,2,"06月01日-06月15日","全面推进国防和军队现代化",4),
            new TeachPlan(2020,2,"06月16日-06月31日","“一国两制”与祖国统一",4),
            new TeachPlan(2020,2,"07月01日-07月07日","中国特色大国外交",2),
    };
}
