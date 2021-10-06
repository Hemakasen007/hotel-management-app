package com.travel.lodge.hotelservice.service;

import com.travel.lodge.hotelservice.dto.CommonResponse;
import com.travel.lodge.hotelservice.exception.HotelDoesNotExistException;
import com.travel.lodge.hotelservice.exception.InvalidDateSelectionException;
import com.travel.lodge.hotelservice.exception.RoomsUnavailableException;
import com.travel.lodge.hotelservice.repository.HotelRepository;
import com.travel.lodge.hotelservice.repository.ReservationRepository;
import com.travel.lodge.hotelservice.repository.RoomRepository;
import com.travel.lodge.hotelservice.util.Consts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import static com.travel.lodge.hotelservice.util.Consts.AppMessages.AVAILABLE_ROOMS;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {

    final HotelRepository hotelRepository;
    final RoomRepository roomRepository;
    final ReservationRepository reservationRepository;

    private static final String DATE_PATTERN = "yyyy-MM-dd hh:mm";

    public CommonResponse getHotels() {
        log.info(Consts.Log.BRACKETS_2, Consts.Log.ENTERED, Consts.Log.GET_HOTELS);
        var hotels = hotelRepository.findAll();
        return new CommonResponse(Consts.ResponseMessages.SUCCESS, null, hotels);

    }


    public CommonResponse checkHotelAvailability(Long hotelId, String checkInDateTime, String checkOutDateTime) throws ParseException {
        log.info(Consts.Log.BRACKETS_2, Consts.Log.ENTERED, Consts.Log.HOTEL_AVAILABILITY);
        var sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            var checkInDate = sdf.parse(checkInDateTime.replace("T", " "));
            var checkOutDate = sdf.parse(checkOutDateTime.replace("T", " "));

            dateValidation(checkInDate, checkOutDate);

            var hotel = hotelRepository.findById(hotelId).orElseThrow(HotelDoesNotExistException::new);

            log.info(Consts.Log.HOTEL_AVAILABLE, hotel.getName());
            var roomsId = reservationRepository.findFilledRoomsForDate(checkInDate, checkOutDate);

            var roomsAll = roomRepository.findAll();
            var copy = new ArrayList<>(roomsAll);
            roomsId.forEach(item -> {
                for (com.travel.lodge.hotelservice.domain.Room room : roomsAll) {
                    if (room.getId().equals(item))
                        copy.remove(room);
                }
            });

            if(copy.isEmpty()){
                throw new RoomsUnavailableException(Consts.ExceptionMessage.NO_ROOMS_AVAILABLE);
            }

            return new CommonResponse(Consts.ResponseMessages.SUCCESS, String.format(AVAILABLE_ROOMS, copy.size()), copy);


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

    }

    private void dateValidation(Date checkIn, Date checkOut){
        var localDCheckIn = checkIn.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        var localDateCheckOut =  checkOut.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        if(localDCheckIn.until(localDateCheckOut, ChronoUnit.DAYS) < 1)
            throw new InvalidDateSelectionException(Consts.ExceptionMessage.DATE_VALIDATION_FAIL);
        if(localDateCheckOut.isBefore(localDCheckIn))
            throw new InvalidDateSelectionException(Consts.ExceptionMessage.DATE_CHECKOUT_EARLY);


    }
}
