package com.scaler.parking_lot.respositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.ParkingLot;

@Repository
public class ParkingLotRepositoryImp implements ParkingLotRepository {
    
    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParkingLotByGateId'");
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParkingLotById'");
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
