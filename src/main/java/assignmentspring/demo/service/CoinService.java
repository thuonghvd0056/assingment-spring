package assignmentspring.demo.service;

import assignmentspring.demo.entity.Coin;
import assignmentspring.demo.repository.MyCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CoinService {
    @Autowired
    MyCoinRepository myCoinRepository;
    public List<Coin> search(String name){
        return myCoinRepository.findAllByNameAndStatus(name, 1);
    }

    public List<Coin> searchByMarketId(long marketId){
        return myCoinRepository.findAllByIdAndStatus(marketId, 1);
    }

    public Coin createNew(Coin coin){
        coin.setId(Calendar.getInstance().getTimeInMillis());
        myCoinRepository.save(coin);
        return coin;
    }

    public List<Coin> getListCoin(){
        return myCoinRepository.findAll();
    }

    public Coin findById(long id){
        return myCoinRepository.findById(id).orElse(null);
    }

}
