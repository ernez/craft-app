package com.ernez.craftapp.exception;

public class DaoException {
    public static class NoData extends BaseException
    {
        public NoData(String msg) {
            super(msg);
        }
    }
}
