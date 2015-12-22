package com.naleid

import ratpack.rx.RxRatpack
import spock.lang.Specification

class MyIpServiceSpec extends Specification {

    def setup() {
        RxRatpack.initialize()
    }

    def "GET will render product JSON"() {
        expect:
        String productId = "123"
//        Product product = new Product(
//                id: productId.toLong(),
//                name: "The Code Book",
//                currencyCode: 'AUS',
//                currentPrice: 19.95
//        )
//
//        ProductService productService = Mock(ProductService)
//        productService.find(productId) >> just(product)

    }

}
