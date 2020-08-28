package com.neardysoft.floorlayout.controllers;

import com.neardysoft.floorlayout.models.Point;
import com.neardysoft.floorlayout.models.Room;
import com.neardysoft.floorlayout.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    private List<Room> roomList = List.of(new Room());

    @BeforeEach
    public void setUp() {
        Mockito.when(roomService.getAllRooms()).thenReturn(roomList);
    }

    @Test
    public void testGetRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/getAllRooms"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}