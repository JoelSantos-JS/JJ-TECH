package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.ItemOrderDTO;
import com.joel.br.JJ.TECH.exceptions.ItemOrderException;
import com.joel.br.JJ.TECH.models.ItemOrder;
import com.joel.br.JJ.TECH.repository.ItemOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderServices {


    private  final ItemOrderRepository repository;


    public ItemOrderServices(ItemOrderRepository repository) {
        this.repository = repository;
    }


    public ItemOrderDTO findById(Long id){
        return repository.findById(id).map(ItemOrderDTO::new).orElseThrow(() -> new ItemOrderException("not found item"));
    }


    public Page<ItemOrderDTO> findAll(Pageable pageable) {
        return  repository.findAll(pageable).map(ItemOrderDTO::new);
    }


    public ItemOrderDTO save(ItemOrderDTO dto){

        ItemOrder itemOrder = new ItemOrder();
        CopyToEntity(itemOrder, dto);
        itemOrder = repository.save(itemOrder);
        return new ItemOrderDTO(itemOrder);

    }




    public void  deleteById(Long id) {
        repository.deleteById(id);

    }

    public void CopyToEntity(ItemOrder itemOrder, ItemOrderDTO it) {
        itemOrder.setQuantity(it.getQuantity());
        itemOrder.setUnityPrice(it.getUnityPrice());
        itemOrder.setTotalPrice(it.getTotalPrice());
        itemOrder.setId(itemOrder.getId());

    }
}
