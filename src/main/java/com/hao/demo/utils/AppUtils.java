package com.hao.demo.utils;

import com.hao.demo.dao.ProductDao;
import com.hao.demo.dto.ProductDto;
import org.springframework.beans.BeanUtils;

public class AppUtils {


    public static ProductDto daoToDto(ProductDao productDao) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productDao, productDto);
        return productDto;
    }

    public static ProductDao dtoToDao(ProductDto productDto) {
        ProductDao productDao = new ProductDao();
        BeanUtils.copyProperties(productDto, productDao);
        return productDao;
    }
}
