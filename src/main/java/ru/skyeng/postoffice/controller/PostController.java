package ru.skyeng.postoffice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skyeng.postoffice.dto.ArriveRq;
import ru.skyeng.postoffice.dto.DeliveryRegistrationRq;
import ru.skyeng.postoffice.service.post.PostServiceImpl;

@RestController
@Tag(name = "Информация по отправлению")
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostServiceImpl postService;

    @Operation(summary = "Регистрация отправления")
    @PostMapping("/registration")
    public void deliveryRegistration(@RequestBody DeliveryRegistrationRq rq) {
        postService.deliveryRegistration(rq);
    }

    @Operation(summary = "убытие из почтового отделения ")
    @PutMapping("/gone/{postalDeliveryId}")
    public void gone(@PathVariable("postalDeliveryId") long id) {
        postService.gone(id);
    }

    @Operation(summary = "прибытие в почтовое отделение")
    @PutMapping("/arrive")
    public void arrive(@RequestBody ArriveRq arriveRq) {
        postService.arrive(arriveRq);
    }


    @Operation(summary = "доставлено")
    @PutMapping("/receive/{postalDeliveryId}")
    public void receive(@PathVariable("postalDeliveryId") long id) {
        postService.receive(id);
    }
}