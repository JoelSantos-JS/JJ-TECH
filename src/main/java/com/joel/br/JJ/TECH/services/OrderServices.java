package com.joel.br.JJ.TECH.services;

import com.joel.br.JJ.TECH.DTO.*;
import com.joel.br.JJ.TECH.exceptions.ItemOrderException;
import com.joel.br.JJ.TECH.exceptions.OrderNotFoundException;
import com.joel.br.JJ.TECH.models.*;
import com.joel.br.JJ.TECH.repository.FormPaymentRepository;
import com.joel.br.JJ.TECH.repository.ItemOrderRepository;
import com.joel.br.JJ.TECH.repository.OrderRepository;
import com.joel.br.JJ.TECH.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServices {

    private final OrderRepository repository;
    private final ItemOrderRepository itemOrderRepository;
    private final FormPaymentRepository formPaymentRepository;

    private final UserRepository userRepository;


    private final UserService userService;

    public OrderServices(OrderRepository repository, ItemOrderRepository itemOrderRepository, FormPaymentRepository formPaymentRepository, UserRepository userRepository, UserService userService) {
        this.repository = repository;
        this.itemOrderRepository = itemOrderRepository;
        this.formPaymentRepository = formPaymentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }



    public Page<OrderDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(OrderDTO::new);
    }

    public OrderDTO findById(Long id){
        return repository.findById(id).map(OrderDTO::new).orElseThrow(() -> new OrderNotFoundException("order not found"));
    }


    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderDTO orderDTO1 =  validateOrder(orderDTO);

        Order order = new Order();

        copyEntity(order, orderDTO1);


        validateAndUpdateItems(order);

        order = repository.save(order);


        return  new OrderDTO(order);
    }




    public OrderDTO validateOrder(OrderDTO orderDTO ) {
        UserDetails user = userService.loadUserByUsername("jane@example.com");
        FormPayment formPayment = formPaymentRepository.findById(orderDTO.getPayment().getId()).orElseThrow(() -> new OrderNotFoundException("order not found"));

        orderDTO.setPayment(new FormPaymentDTO(formPayment));
        orderDTO.setFeeShipping(BigDecimal.valueOf(10));
        orderDTO.setStatus(OrderStatus.PENDING);
        orderDTO.setCode(UUID.randomUUID().toString());

        return orderDTO;
    }

    public void copyEntity(Order order, OrderDTO orderDTO) {
        // 1. Consulte o FormPayment pelo ID
        FormPayment formPayment = formPaymentRepository.findById(orderDTO.getPayment().getId())
                .orElseThrow(() -> new OrderNotFoundException("FormPayment not found"));

        // 2. Obtenha diretamente a entidade User do banco de dados pelo ID ou outro identificador exclusivo
        User user = userRepository.findById(orderDTO.getUser().getId())
                .orElseThrow(() -> new OrderNotFoundException("User not found"));

        // 3. Copie os valores da OrderDTO para a Order
        order.setId(orderDTO.getId());
        order.setCode(orderDTO.getCode());
        order.setSubTotal(orderDTO.getSubTotal());
        order.setValueTotal(orderDTO.getValueTotal());
        order.setFeeShipping(orderDTO.getFeeShipping());
        order.setStatus(orderDTO.getStatus());
        order.setPayment(formPayment);
        order.setUser(user);  // Use a entidade User diretamente
        order.setCreateDate(Instant.now());
        order.setFeeShipping(orderDTO.getFeeShipping());




    }

    public List<ItemOrder> validateAndUpdateItems(Order order) {
        List<ItemOrder> updateItems = new ArrayList<>();



        for (ItemOrder itemOrder : order.getItems()) {
            if(itemOrder.getQuantity() <= 0){
                throw  new ItemOrderException();
            }
            itemOrder.setOrder(order);

            updateItems.add(itemOrder);
        }
    return  updateItems;
    }
}
