package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.Gate;

@Repository
public class GateRepositoryImp implements GateRepository {
   HashMap<Long,Gate> gate=new HashMap<>();
    @Override
    public Optional<Gate> findById(long gateId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Gate save(Gate gate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
