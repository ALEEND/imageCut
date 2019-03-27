package com.mko.test.service;

import static java.sql.JDBCType.NULL;
import static java.util.jar.Pack200.Packer.ERROR;


public interface IStatusMessage {

    String FILE_UPLOAD_ERROR = ERROR;
    Object FILE_UPLOAD_NULL = NULL;


    String getCode();
    String getMessage();

    enum SystemStatus implements IStatusMessage {

        SUCCESS("1000", "操作成功"), //请求成功
        ERROR("1001", "网络异常，请稍后重试~"), ;      //请求失败

        public static final IStatusMessage PARAM_ERROR =ERROR;
        private String code;
        private String message;

        SystemStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }
        public String getCode() {
            return this.code;
        }
        public String getMessage() {
            return this.message;
        }
    }
}
