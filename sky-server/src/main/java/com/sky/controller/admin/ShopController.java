package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String KEY = "SHOP_STATUS";

    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置营业状态：{}", status==1? "营业中":"打烊中");
        redisTemplate.opsForValue().set("KEY",status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("查询营业状态")
    public Result<Integer> getStatus(){
        log.info("查询营业状态");
        Integer status = (Integer) redisTemplate.opsForValue().get("KEY");
        return Result.success(status);
    }
}
