package com.supreme.admin.service;

import com.supreme.admin.model.dto.EventDTO;
import com.supreme.admin.model.dto.NoticeDTO;
import com.supreme.admin.model.dto.ProductDTO;
import com.supreme.admin.model.entity.EventMember;
import com.supreme.admin.model.entity.EventProduct;
import com.supreme.admin.model.entity.Notice;
import com.supreme.admin.model.network.Header;
import com.supreme.admin.model.network.request.EventApiRequest;
import com.supreme.admin.repository.EventMemberRepository;
import com.supreme.admin.repository.EventRepository;
import com.supreme.admin.repository.NoticeRepository;
import com.supreme.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class EventApiService {
    private final NoticeRepository noticeRepository;
    final EventRepository eventRepository;
    final ProductRepository productRepository;
    final EventMemberRepository eventMemberRepository;
    @Transactional
    public List<EventDTO> list(){
        return  EventDTO.fromEntity(eventRepository.findAll());
    }
    public List<EventDTO> list(Long idx){
        return  EventDTO.fromEntity(eventRepository.findById(idx).stream().toList());
    }

    @Transactional
    public Header<EventDTO> create(EventDTO dto){
        EventProduct eventProduct = eventRepository.save(dto.toEntity(dto.productDTO().toEntity()));
        return Header.OK(EventDTO.fromEntity(eventProduct));

    }
    @Transactional(readOnly = true)
    public EventDTO eventDetail(Long eventIdx){
        return eventRepository.findById(eventIdx)
                .map(EventDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("이벤트가 없습니다"));
    }
    @Transactional
    public Header<EventProduct> update(Long idx, Header<EventApiRequest> eventProductRequest) {
        EventApiRequest request = eventProductRequest.getData();
        Optional<EventProduct> eventProduct = eventRepository.findByIdx(idx);

        return eventProduct.map(
                        ep -> {
                            if (request.title() != null) ep.setTitle(request.title());
                            if (request.img() != null)ep.setImg(request.img());
                            if (request.startTime() != null)ep.setStartTime(request.startTime());
                            if (request.endTime() != null)ep.setEndTime(request.endTime());
                            return ep;
                        }).map(ep -> eventRepository.save(ep))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음")
                );
    }
    @Transactional
    public Header delete(Long idx) {
        Optional<EventProduct> eventProduct = eventRepository.findByIdx(idx);
        return eventProduct.map(ep -> {
            eventRepository.delete(ep);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    @Transactional(readOnly = true)
    public List<ProductDTO> genderList(String gender){
        return productRepository.findTop40ByGenderOrderByWishCount(gender).stream().map(ProductDTO::fromEntity).toList();
    }


    public void draw(Long eventIdx){
        System.out.println("@@");
        EventProduct event =eventRepository.findByIdx(eventIdx).get();
        List<EventMember> eventMembers = eventMemberRepository.findDraw(eventIdx);
        String title="[이벤트 발표]"+ event.getTitle();
        String content = "<p>안녕하세요. KREAM 입니다.</p>\n" +
                "<p>이번주 진행되었던,"+ event.getTitle() + " "+ event.getProduct().getNameKor()+" 당첨자를 발표합니다.</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p><strong>[</strong><strong>"+event.getProduct().getName()+"</strong><strong style='font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;'>]</strong></p>\n" +
                "<figure class='table'>\n" +
                "<table>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>가입 이메일 주소</td>\n" +
                "<td>이름</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<p>"+ eventMembers.get(0).getMember().getEmail().substring(0,2)+"******@"+eventMembers.get(0).getMember().getEmail().split("@")[1] +"</p>\n" +
                "</td>\n" +
                "<td>\n" +
                "<p>"+eventMembers.get(0).getMember().getName().substring(0,1)+"*"+eventMembers.get(0).getMember().getName().substring(eventMembers.get(0).getMember().getName().length()-1,eventMembers.get(0).getMember().getName().length()) +"</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<p>"+ eventMembers.get(1).getMember().getEmail().substring(0,2)+"******@"+eventMembers.get(1).getMember().getEmail().split("@")[1] +"</p>\n" +
                "</td>\n" +
                "<td>\n" +
                "<p>"+eventMembers.get(1).getMember().getName().substring(0,1)+"*"+eventMembers.get(1).getMember().getName().substring(eventMembers.get(1).getMember().getName().length()-1,eventMembers.get(1).getMember().getName().length()) +"</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</figure>\n" +
                "<p>총 2명의 당첨을 축하드립니다!</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>당첨자에게는 오늘 중 상품 수령 시 필요한 서류를 가입하신 이메일로 요청하고 있습니다. 서류를 모두 전달받은 후 상품이 발송되는 점 유의 부탁드립니다.</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>감사합니다.</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p><strong>유의사항</strong></p>\n" +
                "<p><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>※</span><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'> </span>당첨자에게는 가입된 회원 정보의 휴대폰 번호로 이벤트 상품의 거래 체결 알림을 드리며, 결제 수단의 문제로 인한 당첨 취소에 대해서는 당사에서 책임지지 않습니다.</p>\n" +
                "<p><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>※</span><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'> </span>응모 시 등록한 결제정보로 결제가 진행되지 않으면 당첨이 즉시 취소되며, 후 순위 당첨자로 변경될 수 있습니다.</p>\n" +
                "<p><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>※</span><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>  </span>당첨자에 한하여 거래 체결 관련 개별 메시지를 발송 드리며, 미당첨자에게는 별도의 연락을 드리지 않습니다.</p>\n" +
                "<p><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>※</span><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>  </span>당첨자에게는 가입된 회원 정보의 이메일로 당첨 안내와 상품 수령을 위한 서류를 요청합니다.</p>\n" +
                "<p><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>※</span><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'> > </span>이메일로 요청한 서류가(신분증/개인정보수집이용동의서) 당첨자 발표 공지 시점으로부터 72시간 내 제출되지 않는 경우 당첨이 취소되며, 재추첨은 진행되지 않습니다.</p>\n" +
                "<p><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>※</span><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'> > </span>상품은 이벤트 참여시 등록된 배송 주소로 배송됩니다.</p>\n" +
                "<p><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'>※</span><span style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;'> > </span>증정받은 상품의 색상 및 사이즈는 랜덤이며 교환이 불가능합니다.</p>";
        NoticeDTO notice = NoticeDTO.of(null,title,content, LocalDateTime.now(),LocalDateTime.now());
        noticeRepository.save(notice.toEntity());

    }
}
