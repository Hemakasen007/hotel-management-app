package com.travel.lodge.hotelservice.service;

import com.travel.lodge.hotelservice.repository.HotelRepository;
import com.travel.lodge.hotelservice.repository.ReservationRepository;
import com.travel.lodge.hotelservice.repository.RoomRepository;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HotelService.class})
@ExtendWith(SpringExtension.class)
public class HotelServiceTest {
    @MockBean
    private HotelRepository hotelRepository;

    @Autowired
    private HotelService hotelService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    public void testCheckHotelAvailability() throws ParseException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        this.hotelService.checkHotelAvailability(123L, "2020-03-01", "2020-03-01");
    }
}

