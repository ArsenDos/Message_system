package kz.com.ars.message_system.controller;

import kz.com.ars.message_system.service.impl.ParserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/receive")
@RequiredArgsConstructor
public class RecieveController {

    private final ParserServiceImpl parserService;


    @PostMapping("/webhook/{chatId}")
    public Mono<ResponseEntity<Void>> webhook(@PathVariable Long chatId){
        return null;
    }
}
