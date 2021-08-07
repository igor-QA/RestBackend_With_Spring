package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.ClientInfo;
import guru.qa.restbackend.domain.LoginInfo;
import guru.qa.restbackend.exeption.InvalidClientNameException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
public class BankController{

    private HashMap<String, ClientInfo> client = new HashMap<>();

    {
        client.put("Maksim", ClientInfo.builder().clientNikName("Mashut").build());
        client.put("Dima", ClientInfo.builder().clientNikName("Digan").build());
        client.put("Igor", ClientInfo.builder().clientNikName("Respekt").build());
    }

    @PostMapping("client/login")
    @ApiOperation("авторизация")
    public ClientInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if(loginInfo.getClientName().equals("Maksim")) {
            return ClientInfo.builder()
                    .loginDate(new Date())
                    .clientName(loginInfo.getClientName())
                    .clientNikName(loginInfo.getClientNikName())
                    .build();
        } else
            throw new InvalidClientNameException();

    }

    @GetMapping("client/getAll")
    @ApiOperation("Получение всех клиентов")
    public List<ClientInfo> getAllClientInfo(){
        return client.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());


    }

}
