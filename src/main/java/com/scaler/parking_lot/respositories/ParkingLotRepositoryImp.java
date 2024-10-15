package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.ParkingLot;

public class ParkingLotRepositoryImp implements ParkingLotRepository{
    Map<Long,ParkingLot> pmap;
    long id=0;
    ParkingLotRepositoryImp()
    {
        this.pmap=new HashMap<>();
    }
    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
       for(Long id:pmap.keySet())
       {
           ParkingLot pp=pmap.get(id);
           int c=0;
           for(Gate g:pp.getGates())
           {
               if(g.getId()==gateId)
               {
                   c=1;
               }
           }
           if(c==1)
           {
               return Optional.of(pp);
           }
       }
       return Optional.empty();
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
       if(pmap.containsKey(id))
       {
           return Optional.of(pmap.get(id));
       }
       else
       {
           return Optional.empty();
       }
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        if(parkingLot.getId()==0)
        {
            parkingLot.setId(++id);
        }
        pmap.put(parkingLot.getId(),parkingLot);
        return parkingLot;
    }
    
}
