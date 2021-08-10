package fpoly.java6.assignment.constant;

public class Constant {

    public final static Integer ORDER_MAX_PRODUCT_PER_PAGE = 6;

    public final static String ADMIN_ROLE = "ROLE_ADMIN";
    public final static String USER_ROLE = "ROLE_USER";

    public final static String EMAIL_SUBJECT_REGISTER_USER = "Xác nhận đăng ký";
    public final static String EMAIL_CONTENT_REGISTER_USER = "<a href='localhost:8080/confirm-account'>Xac nhan tai khoan</a>";

    public static class Image {
        public static String STORE_IMAGE_PATH = "/src/main/resources/static/images/product";
    }

}
