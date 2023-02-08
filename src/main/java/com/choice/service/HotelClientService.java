package com.choice.service;

import com.choice.model.Hotel;
import com.choice.wsdl.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.io.IOException;

public class HotelClientService extends WebServiceGatewaySupport {

    public GetHotelByIdResponse getHotelById(Long hotelId) {
        try {
            GetHotelByIdRequest request = new GetHotelByIdRequest();
            request.setHotelId(hotelId);

            return (GetHotelByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
                    request, new SoapActionCallback("http://localhost:9090/soapws/getHotelByIdRequest"));
        } catch (WebServiceIOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public GetAllHotelsWithFilteringResponse getAllHotelsWithFiltering(Integer pageNumber, Integer pageSize, String nameFilter) {
        try {
            GetAllHotelsWithFilteringRequest request = new GetAllHotelsWithFilteringRequest();
            request.setPageNumber(pageNumber);
            request.setPageSize(pageSize);
            request.setNameFilter(nameFilter);
            return (GetAllHotelsWithFilteringResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/getAllHotelsWithFilteringRequest"));
        } catch (WebServiceIOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public AddHotelResponse addHotel(String name, String address, Integer rating) {
        try {
            AddHotelRequest request = new AddHotelRequest();
            request.setName(name);
            request.setAddress(address);
            request.setRating(rating);
            return (AddHotelResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/addHotelRequest"));
        } catch (WebServiceIOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public UpdateHotelResponse updateHotel(Long hotelId, Hotel hotel) {
        try {
            HotelInfo hotelInfo = new HotelInfo();
            hotelInfo.setHotelId(hotelId);
            hotelInfo.setName(hotel.getName());
            hotelInfo.setAddress(hotel.getAddress());
            hotelInfo.setRating(hotel.getRating());

            UpdateHotelRequest request = new UpdateHotelRequest();
            request.setHotelInfo(hotelInfo);

            return (UpdateHotelResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/updateHotelRequest"));
        } catch (WebServiceIOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public DeleteHotelResponse deleteHotel(Long hotelId) {
        try {
            DeleteHotelRequest request = new DeleteHotelRequest();
            request.setHotelId(hotelId);
            return (DeleteHotelResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/deleteHotelRequest"));
        } catch (WebServiceIOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    public AddAmenityResponse addAmenity(Long amenityId, Long hotelId) {
        try {
            AddAmenityRequest request = new AddAmenityRequest();
            request.setHotelId(hotelId);
            request.setAmenityId(amenityId);
            return (AddAmenityResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/addAmenityRequest"));
        } catch (WebServiceIOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public DeleteAmenityResponse deleteAmenity(Long amenityId, Long hotelId) {
        try {
            DeleteAmenityRequest request = new DeleteAmenityRequest();
            request.setHotelId(hotelId);
            request.setAmenityId(amenityId);
            return (DeleteAmenityResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/deleteAmenityRequest"));
        } catch (WebServiceIOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
