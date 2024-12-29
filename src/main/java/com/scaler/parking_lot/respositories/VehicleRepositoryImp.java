package com.scaler.parking_lot.respositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.Vehicle;

@Repository
public class VehicleRepositoryImp implements VehicleRepository {

    @Override
    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVehicleByRegistrationNumber'");
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
