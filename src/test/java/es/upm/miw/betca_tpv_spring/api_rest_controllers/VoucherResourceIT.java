package es.upm.miw.betca_tpv_spring.api_rest_controllers;

import es.upm.miw.betca_tpv_spring.documents.Voucher;
import es.upm.miw.betca_tpv_spring.dtos.VoucherCreationDto;
import es.upm.miw.betca_tpv_spring.dtos.VoucherDto;
import es.upm.miw.betca_tpv_spring.dtos.VoucherSearchDto;
import es.upm.miw.betca_tpv_spring.repositories.VoucherReactRepository;
import es.upm.miw.betca_tpv_spring.repositories.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static es.upm.miw.betca_tpv_spring.api_rest_controllers.VoucherResource.VOUCHERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ApiTestConfig
public class VoucherResourceIT {

    @Autowired
    private RestService restService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private VoucherRepository voucherRepository;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private LinkedList<Voucher> listVouchers;

    @BeforeEach
    void seedDatabase() {
        listVouchers = new LinkedList<>();

        Stream.iterate(0, i -> i + 1).limit(10)
                .map(i -> new Voucher(new BigDecimal((int) (10 * Math.random()))))
                .forEach(listVouchers::add);

        this.voucherRepository.saveAll(listVouchers);

        System.out.println(listVouchers.toString());
    }

    @Test
    void testCreateVoucher() {
        VoucherCreationDto voucherCreationDto = new VoucherCreationDto(BigDecimal.valueOf(5.05));

        VoucherDto voucher = this.restService.loginAdmin(this.webTestClient)
                .post().uri(contextPath + VOUCHERS)
                .body(BodyInserters.fromObject(voucherCreationDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(VoucherDto.class)
                .returnResult().getResponseBody();

        assertNotNull(voucher);
        assertEquals(BigDecimal.valueOf(5.05), voucher.getValue());
    }

    @Test
    void testCreateVoucherWithNegativeValue(){
        VoucherCreationDto voucherCreationDto = new VoucherCreationDto(BigDecimal.valueOf(-5.05));

        this.restService.loginAdmin(this.webTestClient)
                .post().uri(contextPath + VOUCHERS)
                .body(BodyInserters.fromObject(voucherCreationDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testSearchVoucherWithAllParams() {
        List<VoucherDto> voucherList = this.restService.loginAdmin(this.webTestClient)
                .get()
                .uri(uriBuilder -> uriBuilder
                    .path(contextPath + VOUCHERS)
                    .queryParam("id", listVouchers.get(0).getId())
                    .queryParam("firstDate", listVouchers.get(0).getCreationDate())
                    .queryParam("finalDate", LocalDateTime.now()).build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(VoucherDto.class)
                .returnResult().getResponseBody();

        assertNotNull(voucherList);
        assertEquals(listVouchers.get(0).getId(), voucherList.get(0).getId());
    }

    @Test
    void testSearchVoucherWithBadId() {
        List<VoucherDto> voucherList = this.restService.loginAdmin(this.webTestClient)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(contextPath + VOUCHERS)
                        .queryParam("id", "thisIsAFakeId")
                        .queryParam("firstDate", listVouchers.get(0).getCreationDate())
                        .queryParam("finalDate", LocalDateTime.now()).build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(VoucherDto.class)
                .returnResult().getResponseBody();

        assertEquals(0, voucherList.size());
    }

    @Test
    void testSearchVoucherWithBadDates() {
        List<VoucherDto> voucherList = this.restService.loginAdmin(this.webTestClient)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(contextPath + VOUCHERS)
                        .queryParam("id", listVouchers.get(0).getId())
                        .queryParam("firstDate", LocalDateTime.now().plusDays(2))
                        .queryParam("finalDate", LocalDateTime.now()).build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(VoucherDto.class)
                .returnResult().getResponseBody();

        assertEquals(0, voucherList.size());
    }

    @Test
    void testConsumeVoucher() {
        VoucherDto voucher = this.restService.loginAdmin(this.webTestClient)
                .put().uri(contextPath + VOUCHERS + "/" + listVouchers.get(1).getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(VoucherDto.class)
                .returnResult().getResponseBody();

        assertNotNull(voucher);
        assertEquals(listVouchers.get(1).getId(), voucher.getId());
        assertNotNull(voucher.getDateOfUse());
    }

    @Test
    void testConsumeVoucherWithBadId() {
        this.restService.loginAdmin(this.webTestClient)
                .put().uri(contextPath + VOUCHERS + "/ThisIsAFakeId" )
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPrintVoucher() {
        this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + VOUCHERS + "/" + listVouchers.get(2).getId() + "/print" )
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testPrintVoucherWithBadId() {
        this.restService.loginAdmin(this.webTestClient)
                .get().uri(contextPath + VOUCHERS + "/thisIsAFakeId" + "/print" )
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    void testCreateAndPrint() {
        VoucherCreationDto voucherCreationDto = new VoucherCreationDto(BigDecimal.valueOf(5.05));

        this.restService.loginAdmin(this.webTestClient)
                .post().uri(contextPath + VOUCHERS + "/print")
                .body(BodyInserters.fromObject(voucherCreationDto))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreateAndPrintWithNegativeValue() {
        VoucherCreationDto voucherCreationDto = new VoucherCreationDto(BigDecimal.valueOf(-5.05));

        this.restService.loginAdmin(this.webTestClient)
                .post().uri(contextPath + VOUCHERS + "/print")
                .body(BodyInserters.fromObject(voucherCreationDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

}
