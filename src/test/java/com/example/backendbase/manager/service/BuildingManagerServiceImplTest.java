package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.user.util.CurrentUserUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuildingManagerServiceImplTest {

    @Autowired
    BuildingManagerService buildingManagerServiceImpl;

    //getBuilding
    //quy tắc đặt tên
    @Test
    void Given_ExactValueIdBuilding_When_getBuilding_Then_SuccessResultsInDatabase() {
        //bản chất của unit test là test funtion và test độ bao phủ
        Buildings building = buildingManagerServiceImpl.getBuilding(2L);

        //kiểm tra custom 1 giá trị bất kỳ dựa trên kết quả mình mong muốn
        assertEquals(2L, building.getId());
        assertEquals("ThanhCong", building.getBuildingName());
        assertEquals(40, building.getTotalRooms());
        assertEquals(4, building.getTotalFloors());


    }

    @Test
    void Given_WrongValueIdBuilding_When_getBuilding_Then_ThrowException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            buildingManagerServiceImpl.getBuilding(0L);
        });

        assertEquals("Error: Buildings is not found.", exception.getMessage());

    }

    @Test
    void Given_NullValueIdBuilding_When_getBuilding_Then_ThrowException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buildingManagerServiceImpl.getBuilding(null);
        });
        String messerror = "The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!";

        assertEquals(messerror, exception.getMessage());

    }

    //UpdateBuilding

    @Test
    void Given_ExactValueValueIdBuilding_When_update_Then_SuccessResultsInDatabase() {

        //mock user
        try (MockedStatic<CurrentUserUtils> utilities = Mockito.mockStatic(CurrentUserUtils.class)) {
            utilities.when(CurrentUserUtils::getCurrentUser).thenReturn("demo user");

            //create request to update
            UpdateBuildingRequest request = UpdateBuildingRequest.builder()
                    .buildingId(1L)
                    .buildingName("abc-test")
                    .totalRoom(10)
                    .totalFloor(10)
                    .city("demo")
                    .district("abc")
                    .wards("abc")
                    .moreAddressDetail("day la detail")
                    .build();

            //update
            buildingManagerServiceImpl.updateBuilding(request);


            //check result of update
            Buildings building = buildingManagerServiceImpl.getBuilding(1L);

            //check bulding
            assertEquals(1L, building.getId());
            assertEquals("abc-test", building.getBuildingName());
            assertEquals(10, building.getTotalRooms());
            assertEquals(10, building.getTotalFloors());

            //check adresss
            assertEquals("demo", building.getAddress().getCity());
            assertEquals("abc", building.getAddress().getDistrict());
            assertEquals("abc", building.getAddress().getWards());
            assertEquals("day la detail", building.getAddress().getMoreDetails());

        }

    }

    @Test
    void Given_WrongValueValueIdBuilding_When_update_Then_ThrowException() {

        //mock user
        try (MockedStatic<CurrentUserUtils> utilities = Mockito.mockStatic(CurrentUserUtils.class)) {
            utilities.when(CurrentUserUtils::getCurrentUser).thenReturn("demo user");

            //create request to update
            UpdateBuildingRequest request = UpdateBuildingRequest.builder()
                    .buildingId(0L)
                    .buildingName("abc-test")
                    .totalRoom(10)
                    .totalFloor(10)
                    .city("demo")
                    .district("abc")
                    .wards("abc")
                    .moreAddressDetail("day la detail")
                    .build();

            //update
            Exception exception = assertThrows(RuntimeException.class, () -> {
                buildingManagerServiceImpl.updateBuilding(request);
            });

            assertEquals("Error: Buildings is not found.", exception.getMessage());

        }

    }

    @Test
    void Given_NullValueValueIdBuilding_When_update_Then_ThrowException() {

        //mock user
        try (MockedStatic<CurrentUserUtils> utilities = Mockito.mockStatic(CurrentUserUtils.class)) {
            utilities.when(CurrentUserUtils::getCurrentUser).thenReturn("demo user");

            //create request to update
            UpdateBuildingRequest request = UpdateBuildingRequest.builder()
                    .buildingId(null)
                    .buildingName("abc-test")
                    .totalRoom(10)
                    .totalFloor(10)
                    .city("demo")
                    .district("abc")
                    .wards("abc")
                    .moreAddressDetail("day la detail")
                    .build();

            //update
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                buildingManagerServiceImpl.getBuilding(null);
            });
            String messerror = "The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!";

            assertEquals(messerror, exception.getMessage());

        }

    }
}