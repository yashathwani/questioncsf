package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.Gate;

@Repository
public class GateRepositoryImp implements GateRepository {
   HashMap<Long,Gate> gates=new HashMap<>();
   private Long id=1L;
    @Override
    public Optional<Gate> findById(long gateId) {
        if(gates.containsKey(gateId))
        {
           return Optional.of(gates.get(gateId));
        }
        else
        {
            return Optional.of(null);
        }
    }

    @Override
    public Gate save(Gate gate) {
        gate.setId(id);
        gates.put(id,gate);
        return gate;
    }
    
}
