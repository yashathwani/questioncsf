package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.ParkingLot;

@Repository
public class ParkingLotRepositoryImp implements ParkingLotRepository {
    HashMap<Long,ParkingLot> lots=new HashMap<>();
    long idx=1;
    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
       for(ParkingLot lot:lots.values())
       {
           for(Gate gate:lot.getGates())
           {
               if(gate.getId()==gateId)
               {
                   return Optional.of(lot);
               }
           }
       }
       return Optional.of(null);
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
      if(lots.containsKey(id))
      {
          return Optional.of(lots.get(id));
      }
      else
      {
          return Optional.of(null);
      }
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        lots.put(idx,parkingLot);
        parkingLot.setId(idx);
        idx++;
        return parkingLot;
    }
    
}
