package com.springshell.eshop;

import com.springshell.eshop.domain.entity.*;
import com.springshell.eshop.domain.enums.Color;
import com.springshell.eshop.domain.enums.Size;
import com.springshell.eshop.domain.enums.Status;
import com.springshell.eshop.service.*;
import com.springshell.eshop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@org.springframework.core.annotation.Order(-1)
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MobileService mobileService;
    @Autowired
    private AccessoryService accessoryService;

    @Override
    public void run(String... args) throws Exception {

        Account account1 = Account.builder()
                .name("dias")
                .surname("arkharov")
                .login("dias")
                .password("777")
                .email("dias1998")
                .phoneNumber("89869074943")
                .build();

        Account account2 = Account.builder()
                .name("ivan")
                .surname("ivanov")
                .login("ivan")
                .password("888")
                .email("ivan1998")
                .phoneNumber("8777777777")
                .build();

        Account account3 = Account.builder()
                .name("airat")
                .surname("airatov")
                .login("airat")
                .password("45888")
                .email("airat1998")
                .phoneNumber("8777999777")
                .build();

        Account account4 = Account.builder()
                .name("sveta")
                .surname("svetova")
                .login("sveta")
                .password("45888")
                .email("sveta1998")
                .phoneNumber("8777999456")
                .build();

        accountService.create(account1);
        accountService.create(account2);
        accountService.create(account3);
        accountService.create(account4);

//        System.out.println(accountService.findByLogin("ivan"));
//        System.out.println(accountService.findByLogin("dias"));

        Customer customer1 = Customer.builder()
                .name("am233dmg")
                .account(accountService.findByLogin("ivan"))
                .build();

        Account dias = accountService.findByLogin("dias");

        Customer customer2 = Customer.builder()
                .name("ggwp")
                .account(dias)
                .build();


        //account1.setCustomer(customer1);
        //account2.setCustomer(customer2);

        //accountService.create(account1);
        //accountService.create(account2);

        customerService.create(customer1);
        customerService.create(customer2);

        Order order1 = Order.builder()
                .orderedDate(DateUtil.convert("July 15, 2021"))
                .shippedDate(DateUtil.convert("August 30, 2021"))
                .status(Status.NEW)
                .totalPrice("123")
                .customer(customerService.findById(1L))
                .build();

        Order order2 = Order.builder()
                .orderedDate(DateUtil.convert("June 15, 2021"))
                .shippedDate(DateUtil.convert("December 30, 2021"))
                .status(Status.NEW)
                .totalPrice("123")
                .customer(customerService.findById(2L))
                .build();

        orderService.create(order1);
        orderService.create(order2);

        //System.out.println(customerService.findById(2L));
        Account ivan = accountService.findByLogin("ivan");
        /*Customer customer = ivan.getCustomer();
        Hibernate.initialize(customer);
        List<Order> orderList = customer.getOrders();
        Hibernate.initialize(orderList);*/
        //System.out.println(ivan.getCustomer().getName());
        /*Order order3 =  orderService.findById(1L);
        order3.setCustomer(customer1);
        orderService.create(order3);*/

        /*Product product1 = Product.builder()
                .description("product1")
                .price(123d)
                .order(orderService.findById(1L))
                .build();

        Product product2 = Product.builder()
                .description("product1")
                .price(321d)
                .order(orderService.findById(2L))
                .build();

        productService.create(product1);
        productService.create(product2);*/


        Mobile mobile1 = Mobile.builder()
                .name("mobileName1")
                .description("mobile1")
                .price(1213d)
                .order(orderService.findById(1L))
                .model("Redmi Note 10T")
                .brand("Xiaomi")
                .build();

        Mobile mobile2 = Mobile.builder()
                .name("mobileName2")
                .description("mobile2")
                .price(5469d)
                .order(orderService.findById(2L))
                .model("Note 10")
                .brand("Samsung")
                .build();

        Mobile mobile3 = Mobile.builder()
                .name("mobileName3")
                .description("mobile3")
                .price(546465d)
                .order(orderService.findById(2L))
                .model("Galaxy Z")
                .brand("Samsung")
                .build();


        mobileService.create(mobile1);
        mobileService.create(mobile2);
        mobileService.create(mobile3);

        Product product = mobileService.findById(1L);

//        System.out.println(product.getDescription());
//        System.out.println(product.getPrice());

        Accessory accessory1 = Accessory.builder()
                .name("accessoryName1")
                .description("accessory1")
                .price(753d)
                .color(Color.BLUE)
                .size(Size.MEDIUM)
                .order(orderService.findById(1L))
                .build();

        Accessory accessory2 = Accessory.builder()
                .name("accessoryName2")
                .description("accessory2")
                .price(7457d)
                .color(Color.RED)
                .size(Size.SMALL)
                .order(orderService.findById(2L))
                .build();

        Accessory accessory3 = Accessory.builder()
                .name("accessoryName3")
                .description("accessory3")
                .price(8457d)
                .color(Color.GREEN)
                .size(Size.SMALL)
                .order(orderService.findById(2L))
                .build();

        accessoryService.create(accessory1);
        accessoryService.create(accessory2);
        accessoryService.create(accessory3);

//        Product product1 = accessoryService.findByName("accessoryName1");
//        System.out.println(product1.getPrice());
//        System.out.println(product1.getDescription());
    }
}
