package com.example.backendbase.manager.constant;

public interface ManagerConstant {
    // filter contract condition
    String ALMOST_EXPIRED_CONTRACT = "almostExpired";
    String LATEST_CONTRACT = "latest";
    String EXPIRED_CONTRACT = "expired";

    //filter staff account condition
    int DEACTIVATE_ACCOUNT = 0;
    int ACTIVATE_ACCOUNT = 1;
    int NONE_FILTER_DEACTIVATE = -1;


    // permission
    int PERMISSION_MATERIAL = 1;
    int PERMISSION_MONEY = 2;
    int PERMISSION_RECEIPT = 3;
    int PERMISSION_CONTRACT = 4;

    int[] PERMISSION_ALL = new int[]{PERMISSION_MATERIAL, PERMISSION_MONEY, PERMISSION_RECEIPT, PERMISSION_CONTRACT};

    //assets type
    Long TYPE_BATH_ROOM = 1L;
    Long TYPE_LIVING_ROOM = 2L;
    Long TYPE_KITCHEN = 3L;
    Long TYPE_BED_ROOM = 4L;
    Long TYPE_ANOTHER = 5L;
    Long TYPE_OFFICE = 6L;


}
