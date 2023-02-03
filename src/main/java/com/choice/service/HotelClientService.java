package com.choice.service;

import com.choice.model.Hotel;
import com.choice.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class HotelClientService extends WebServiceGatewaySupport {

    public GetHotelByIdResponse getHotelById(Long hotelId) {

        GetHotelByIdRequest request = new GetHotelByIdRequest();
        request.setHotelId(hotelId);

        return (GetHotelByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/getHotelByIdRequest"));
    }

    public GetAllHotelsWithFilteringResponse getAllHotelsWithFiltering(Integer pageNumber, Integer pageSize, String nameFilter) {
        GetAllHotelsWithFilteringRequest request = new GetAllHotelsWithFilteringRequest();
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        request.setNameFilter(nameFilter);
        return (GetAllHotelsWithFilteringResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/getAllHotelsWithFilteringRequest"));
    }

    public AddHotelResponse addHotel(String name, String address, Integer rating) {
        AddHotelRequest request = new AddHotelRequest();
        request.setName(name);
        request.setAddress(address);
        request.setRating(rating);
        return (AddHotelResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/addHotelRequest"));
    }

    public UpdateHotelResponse updateHotel(Long hotelId, Hotel hotel) {
        HotelInfo hotelInfo = new HotelInfo();
        hotelInfo.setHotelId(hotelId);
        hotelInfo.setName(hotel.getName());
        hotelInfo.setAddress(hotel.getAddress());
        hotelInfo.setRating(hotel.getRating());

        UpdateHotelRequest request = new UpdateHotelRequest();
        request.setHotelInfo(hotelInfo);

        return (UpdateHotelResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/updateHotelRequest"));
    }

    public DeleteHotelResponse deleteHotel(Long hotelId) {
        DeleteHotelRequest request = new DeleteHotelRequest();
        request.setHotelId(hotelId);
        return (DeleteHotelResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/deleteHotelRequest"));
    }

    public AddAmenityResponse addAmenity(Long amenityId, Long hotelId) {
        AddAmenityRequest request = new AddAmenityRequest();
        request.setHotelId(hotelId);
        request.setAmenityId(amenityId);
        return (AddAmenityResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/addAmenityRequest"));
    }

    public DeleteAmenityResponse deleteAmenity(Long amenityId, Long hotelId) {
        DeleteAmenityRequest request = new DeleteAmenityRequest();
        request.setHotelId(hotelId);
        request.setAmenityId(amenityId);
        return (DeleteAmenityResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:9090/soapws/deleteAmenityRequest"));
    }
}
