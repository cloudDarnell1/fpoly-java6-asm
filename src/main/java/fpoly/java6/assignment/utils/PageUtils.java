package fpoly.java6.assignment.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static List<Integer> getPages(Integer totalPages) {
        List<Integer> pages = new ArrayList<>();

        if (isOnlyOnePage(totalPages)) {
            return pages;
        }

        for (int i = 1; i <= totalPages; i++) {
            pages.add(i);
        }

        return pages;
    }

    private static boolean isOnlyOnePage(Integer totalPages) {return totalPages == 1;}

}
