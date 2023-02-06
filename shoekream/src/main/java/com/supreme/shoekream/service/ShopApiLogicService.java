package com.supreme.shoekream.service;

import com.supreme.shoekream.model.dto.ProductDTO;
import com.supreme.shoekream.model.entity.Member;
import com.supreme.shoekream.model.entity.Product;
import com.supreme.shoekream.model.enumclass.SearchType;
import com.supreme.shoekream.model.network.Header;
import com.supreme.shoekream.model.network.Pagination;
import com.supreme.shoekream.model.network.request.ProductApiRequest;
import com.supreme.shoekream.model.network.response.ProductApiResponse;
import com.supreme.shoekream.repository.MemberRepository;
import com.supreme.shoekream.repository.ProductRepository;
import com.supreme.shoekream.repository.TagRepository;
import com.supreme.shoekream.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopApiLogicService extends BaseService<ProductApiRequest, ProductApiResponse, Product> {
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final WishRepository wishRepository;
    private final TagRepository tagRepository;

    private ProductApiResponse response(Product product){
        ProductApiResponse productApiResponse = ProductApiResponse.builder()
                .idx(product.getIdx())
                .name(product.getName())
                .nameKor(product.getNameKor())
                .img(product.getImg())
                .brand(product.getBrand())
                .size(product.getSize())
                .category(product.getCategory())
//                .wishCount(product.getWishCount())
                .modelNum(product.getModelNum())
                .releaseDate(product.getReleaseDate())
                .color(product.getColor())
                .firstPrice(product.getFirstPrice())
                .gender(product.getGender())
                .collection(product.getCollection())
                .build();
        return productApiResponse;
    }

    @Override
    public Header<ProductApiResponse> create(Header<ProductApiRequest> request) {
        ProductApiRequest productApiRequest = request.getData();
        Product product = Product.builder()
                .name(productApiRequest.getName())
                .nameKor(productApiRequest.getNameKor())
                .img(productApiRequest.getImg())
                .brand(productApiRequest.getBrand())
                .size(productApiRequest.getSize())
                .category(productApiRequest.getCategory())
                .modelNum(productApiRequest.getModelNum())
                .releaseDate(productApiRequest.getReleaseDate())
                .color(productApiRequest.getColor())
                .firstPrice(productApiRequest.getFirstPrice())
                .gender(productApiRequest.getGender())
                .collection(productApiRequest.getCollection())
                .build();
        Product newProduct = baseRepository.save(product);
        return Header.OK(response(newProduct));
    }

    @Override
    public Header<ProductApiResponse> read(Long idx) {
        return productRepository.findById(idx).map(product-> response(product))
                .map(Header::OK).orElseGet(() -> Header.ERROR("상품 없음!"));
    }

    @Override
    public Header<ProductApiResponse> update(Header<ProductApiRequest> request) {
        ProductApiRequest productApiRequest = request.getData();
        Optional<Product> products = productRepository.findById(productApiRequest.getIdx());
        return products.map(
                        product -> {
                            product.setBrand(productApiRequest.getBrand());
                            product.setName(productApiRequest.getName());
                            product.setNameKor(productApiRequest.getNameKor());
                            product.setSize(productApiRequest.getSize());
                            product.setImg(productApiRequest.getImg());
                            product.setModelNum(productApiRequest.getModelNum());
                            product.setReleaseDate(productApiRequest.getReleaseDate());
                            product.setFirstPrice(productApiRequest.getFirstPrice());
                            product.setColor(productApiRequest.getColor());
                            product.setCategory(productApiRequest.getCategory());
                            product.setGender(productApiRequest.getGender());
                            product.setCollection(productApiRequest.getCollection());
                            return product;
                        }).map(product -> productRepository.save(product))
                .map(product -> response(product))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("상품 없음!")
                );
    }

    @Override
    public Header delete(Long idx) {
        Optional<Product> products = productRepository.findById(idx);
        return products.map(product->{
            productRepository.delete(product);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<List<ProductApiResponse>> search(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductApiResponse> productApiResponses = products.stream().map(
                product -> response(product)).collect(Collectors.toList());
        Pagination pagination = Pagination.builder()
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .currentPage(products.getNumber())
                .currentElements(products.getNumberOfElements())
                .build();
        return Header.OK(productApiResponses, pagination);
    }

    public List<ProductDTO> categoryList(String category){
        return productRepository.findByCategory(category).stream().map(ProductDTO::fromEntity).toList();
    }

    //카테고리 기능
    @Transactional(readOnly = true)// 데이터에 권한을 주지 않는다
    public Page<ProductDTO> searchProduct(SearchType searchType, List<String> searchKeyword, Pageable pageable){
        if (searchKeyword == null || searchKeyword.isEmpty()){
            return productRepository.findAll(pageable).map(ProductDTO::fromEntity);
        }
        return switch (searchType){
            case CATEGORY -> productRepository.findByCategoryIn(searchKeyword, pageable).map(ProductDTO::fromEntity);
            case BRAND -> productRepository.findByBrandIn(searchKeyword, pageable).map(ProductDTO::fromEntity);
            case COLLECTION -> productRepository.findByCollectionIn(searchKeyword, pageable).map(ProductDTO::fromEntity);
            case GENDER -> productRepository.findByGender(searchKeyword.toString(), pageable).map(ProductDTO::fromEntity);
            case FIRSTPRICE -> productRepository.findByFirstPrice(searchKeyword.toString(), pageable).map(ProductDTO::fromEntity);
        };
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> searchsProduct(String size, String brand, String category, String collection,
                                           String gender, String keyword, Pageable pageable){
        if (size==null && brand==null && category==null && collection==null && gender==null && keyword==null ){
            return productRepository.findBySizeContaining(" ", pageable).map(ProductDTO::fromEntity);
        }
        if (size == null) size="";
        if (brand == null) brand="";
        if (category == null) category="";
        if (collection == null) collection="";
        if (gender == null) gender="";
        if (keyword == null) keyword="";
        return productRepository.findBySizeContainingAndBrandContainingAndCategoryContainingAndCollectionContainingAndGenderContainingAndNameKorContaining(
                size,brand,category,collection,gender,keyword,pageable).map(ProductDTO::fromEntity);
    }


      public List<String> getBrands(){
        List<String> brands = productRepository.findAllDistinctBrands();
        Collections.sort(brands);
        return brands;
    }
    @Transactional(readOnly = true)
    public Page<ProductDTO> brand(String brandName, Pageable pageable){
        return productRepository.findByBrand(brandName,pageable).map(ProductDTO::fromEntity);
    }



    public List<Boolean> isWish(List<Product> products, Long memberIdx){
        Member member = memberRepository.findById(memberIdx).get();
        List<Boolean> isWish = new ArrayList<>();
        products.forEach(
                product -> {
                    isWish.add(wishRepository.existsByMemberAndProduct(member,product));
                }
        );
        return isWish;
    }

    public List<Long> wishCount(List<Product> products){
        List<Long> wishCount = new ArrayList<>();
        products.forEach(
                product -> {
                    wishCount.add(wishRepository.countByProduct(product));
                }
        );
        return wishCount;
    }

    public List<Long> tagCount(List<Product> products){
        List<Long> tagCount = new ArrayList<>();
        products.forEach(
                product -> {
                    final long[] totalCount = {0L};
                    List<Product> sameNameProducts = productRepository.findAllByName(product.getName());
                    sameNameProducts.stream().mapToLong(tagRepository::countByProduct
                    ).forEach(count -> totalCount[0] +=count);
                    tagCount.add(totalCount[0]);
                });
        return tagCount;
    }
}

