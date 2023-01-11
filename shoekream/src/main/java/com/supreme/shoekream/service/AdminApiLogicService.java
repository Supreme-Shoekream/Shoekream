package com.supreme.shoekream.service;

import com.supreme.shoekream.model.entity.Admin;
import com.supreme.shoekream.model.enumclass.UserStatus;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.AdminApiRequest;
import com.supreme.shoekream.model.network.response.AdminApiResponse;
import com.supreme.shoekream.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class AdminApiLogicService extends BaseService<AdminApiRequest, AdminApiResponse, Admin>{

    private final AdminRepository adminRepository;

    private AdminApiResponse response(Admin admin){
        AdminApiResponse adminUserApiResponse = AdminApiResponse.builder()
                .idx(admin.getIdx())
                .adminid(admin.getAdminid())
                .adminpw(admin.getAdminpw())
                .name(admin.getName())
                .hp(admin.getHp())
                .createdAt(now())
                .status(admin.getStatus())
                .build();
        return adminUserApiResponse;
    }

    @Override
    public Header<AdminApiResponse> create(Header<AdminApiRequest> request) {
        AdminApiRequest adminUserApiRequest = request.getData();

        Admin admin = Admin.builder()
                .adminid(adminUserApiRequest.getAdminid())
                .adminpw(adminUserApiRequest.getAdminpw())
                .name(adminUserApiRequest.getName())
                .hp(adminUserApiRequest.getHp())
                .status(UserStatus.REGISTERED)
                .build();
        Admin newUsers = adminRepository.save(admin);
        return Header.OK(response(newUsers));
    }

    @Override
    public Header<AdminApiResponse> read(Long idx) {
        return adminRepository.findById(idx).map(admins -> response(admins))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터 없음"));
    }
    public Header<AdminApiResponse> read(String userid, String userpw) {
        return adminRepository.findByAdminidAndAdminpw(userid,userpw).map(
                admin -> response(admin)).map(Header::OK)
                .orElseGet(()-> Header.ERROR("아이디 또는 비밀번호가 틀렸습니다.")
        );
    }

    @Override
    public Header<AdminApiResponse> update(Header<AdminApiRequest> request) {
        AdminApiRequest adminApiRequest = request.getData();
        Optional<Admin> admins = adminRepository.findByAdminid(adminApiRequest.getAdminid());
        return admins.map(
                        admin -> {
                            admin.setName(adminApiRequest.getName());
                            admin.setHp(adminApiRequest.getHp());
                            return admin;
                        }).map(admin -> adminRepository.save(admin))
                .map(admin -> response(admin))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header delete(Long idx) {
        Optional<Admin> admins = adminRepository.findById(idx);
        return admins.map(admin->{
            adminRepository.delete(admin);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<AdminApiResponse> login(Header<AdminApiRequest> request){
        AdminApiRequest adminUserApiRequest = request.getData();
        Optional<Admin> adminUser =adminRepository.findByAdminidAndAdminpw(
                adminUserApiRequest.getAdminid(),adminUserApiRequest.getAdminpw()
        );
        if (!adminUser.isEmpty()){
            return Header.OK();
        }
        return Header.ERROR("아이디 또는 비밀번호가 틀렸음!");
    }

    //참고 페이지네이션 리스트
    public Header<List<AdminApiResponse>> search(Pageable pageable){
        Page<Admin> admins = adminRepository.findAll(pageable);

        List<AdminApiResponse> adminApiResponses = admins.stream().map(
                admin -> response(admin)).collect(Collectors.toList());
        //collect 특정 자료구조로 바꿔달라는 뜻 그중에서도 List 형태!!
        Pagination pagination = Pagination.builder()
                .totalPages(admins.getTotalPages())             //전체 페이지수
                .totalElements(admins.getTotalElements())       //전체 페이지 요소
                .currentPage(admins.getNumber())                //현재 페이지
                .currentElements(admins.getNumberOfElements())   //현재 페이지 요소
                .build();
        return Header.OK(adminApiResponses, pagination);
    }
}