package com.transitclone.manager;


import com.transitclone.responses.NearbySearchResponse.NearBySearchResponse;
import com.transitclone.responses.direction.GoogleDirectionResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by think360 on 18/04/17.
 */

public interface ApiService {


    @GET("/maps/api/directions/json")
    Observable<GoogleDirectionResponse> getWayPoints(@Query("origin") String origin,
                                                     @Query("destination") String destination,@Query("mode") String mode,
                                                     @Query("key") String key);

    @GET("/maps/api/place/nearbysearch/json?")
    Observable<NearBySearchResponse> getBuStops(@Query("location") String location,
                                                @Query("radius") String radius,
                                                @Query("types") String types,
                                                @Query("sensor") boolean sensor,
                                                @Query("key") String key);





/*
    @FormUrlEncoded
    @POST("userLogin")
    Observable<LoginResponse> loginApi(@Field("email") String Email,
                                       @Field("password") String Password);


    @FormUrlEncoded
    @POST("userRegister")
    Observable<RegisterResponse> registerApi(@Field("fromLocation") String name,
                                             @Field("email") String Email,
                                             @Field("password") String Password,
                                             @Field("phoneNo") String phoneNo);

    @FormUrlEncoded
    @POST("registerPhoneNo")
    Observable<RegisterPhoneNoResponse> registerPhoneNoApi(@Field("phoneNo") String phoneNo);

    @FormUrlEncoded
    @POST("validateOTP")
    Observable<VerifyOtpResponse> validateOTPApi(@Field("phoneNo") String phoneNo,
                                                 @Field("OTP") String otp);

    @FormUrlEncoded
    @POST("updateDeviceIdAndToken")
    Observable<UpdateDeviceIdAndTokenResponse> updateDeviceIdAndTokenAPI(@Field("userId") String userId,
                                                                         @Field("deviceId") String deviceId,
                                                                         @Field("deviceToken") String deviceToken);

    @FormUrlEncoded
    @POST("getNearestLatLng")
    Observable<NearestLatLngResponse> getNearestLatLngAPI(@Field("userId") String userId,
                                                          @Field("lat") String lat,
                                                          @Field("lng") String lng);

    @GET("/maps/api/directions/json")
    Observable<GoogleDirectionResponse> getWayPoints(@Query("origin") String origin,
                                                     @Query("destination") String destination,
                                                     @Query("key") String key);


    @FormUrlEncoded
    @POST("rideRequest")
    Observable<RideRequestResponse> rideRequestAPI(@Field("userId") String userId,
                                                   @Field("vehicleType") String vehicleType,
                                                   @Field("status") String status,
                                                   @Field("fromAddress") String fromAddress,
                                                   @Field("fromLat") String fromLat,
                                                   @Field("fromLng") String fromLng,
                                                   @Field("toAddress") String toAddress,
                                                   @Field("toLat") String toLat,
                                                   @Field("toLng") String toLng,
                                                   @Field("driverId") String driverId);


    @FormUrlEncoded
    @POST("getHistoryOfrideRequest")
    Observable<RideHistoryResponse> getRideHistory(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("getScheduleOfrideRequest")
    Observable<ScheduledRideResponse> getScheduledRides(@Field("userId") String userId);


    @FormUrlEncoded
    @POST("fareEstimate")
    Observable<RideFareResponse> getRideFareAPI(@Field("fromLat") String fromLat,
                                                @Field("fromLng") String fromLng,
                                                @Field("toLat") String toLat,
                                                @Field("toLng") String toLng,
                                                @Field("vehicleType") String vehicleType,
                                                @Field("model") String model,
                                                @Field("unit") String unit);
*/

}
