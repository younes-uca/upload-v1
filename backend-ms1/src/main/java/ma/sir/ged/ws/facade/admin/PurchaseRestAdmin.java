package  ma.sir.ged.ws.facade.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import ma.sir.ged.bean.core.Purchase;
import ma.sir.ged.bean.history.PurchaseHistory;
import ma.sir.ged.dao.criteria.core.PurchaseCriteria;
import ma.sir.ged.dao.criteria.history.PurchaseHistoryCriteria;
import ma.sir.ged.service.facade.admin.PurchaseAdminService;
import ma.sir.ged.ws.converter.PurchaseConverter;
import ma.sir.ged.ws.dto.PurchaseDto;
import ma.sir.ged.zynerator.controller.AbstractController;
import ma.sir.ged.zynerator.dto.AuditEntityDto;
import ma.sir.ged.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.sir.ged.zynerator.process.Result;

import org.springframework.http.HttpEntity;

import org.springframework.web.multipart.MultipartFile;
import ma.sir.ged.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/purchase/")
public class PurchaseRestAdmin  extends AbstractController<Purchase, PurchaseDto, PurchaseHistory, PurchaseCriteria, PurchaseHistoryCriteria, PurchaseAdminService, PurchaseConverter> {


    @Operation(summary = "Import excel")
    @PostMapping("import-excel")
    public ResponseEntity<List<Purchase>> importExcel(@RequestParam("file") MultipartFile file){
        return super.importExcel(file);
    }

    @Operation(summary = "Exporte pdf")
    @PostMapping("exportPdf/")
    public HttpEntity<byte[]> createPdf(@RequestBody PurchaseDto dto) throws Exception{
        return service.createPdf(dto);
    }
    @Operation(summary = "upload one purchase")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple purchases")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all purchases")
    @GetMapping("")
    public ResponseEntity<List<PurchaseDto>> findAll() throws Exception {
        return super.findAll();
    }

    @Operation(summary = "Finds an optimized list of all purchases")
    @GetMapping("optimized")
    public ResponseEntity<List<PurchaseDto>> findAllOptimized() throws Exception {
        return super.findAllOptimized();
    }

    @Operation(summary = "Finds a purchase by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PurchaseDto> findById(@PathVariable Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }
    @Operation(summary = "Saves the specified  purchase")
    @PostMapping("")
    public ResponseEntity<PurchaseDto> save(@RequestBody PurchaseDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  purchase")
    @PutMapping("")
    public ResponseEntity<PurchaseDto> update(@RequestBody PurchaseDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of purchase")
    @PostMapping("multiple")
    public ResponseEntity<List<PurchaseDto>> delete(@RequestBody List<PurchaseDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified purchase")
    @DeleteMapping("")
    public ResponseEntity<PurchaseDto> delete(@RequestBody PurchaseDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified purchase")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple purchases by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "find by client id")
    @GetMapping("client/id/{id}")
    public List<Purchase> findByClientId(@PathVariable Long id){
        return service.findByClientId(id);
    }
    @Operation(summary = "delete by client id")
    @DeleteMapping("client/id/{id}")
    public int deleteByClientId(@PathVariable Long id){
        return service.deleteByClientId(id);
    }
    @Operation(summary = "Finds a purchase and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PurchaseDto> findWithAssociatedLists(@PathVariable Long id) {
        return super.findWithAssociatedLists(id);
    }

    @Operation(summary = "Finds purchases by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PurchaseDto>> findByCriteria(@RequestBody PurchaseCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated purchases by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PurchaseCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports purchases by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody PurchaseCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets purchase data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PurchaseCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }

    @Operation(summary = "Gets purchase history by id")
    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @Operation(summary = "Gets purchase paginated history by criteria")
    @PostMapping("history-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody PurchaseHistoryCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports purchase history by criteria")
    @PostMapping("export-history")
    public ResponseEntity<InputStreamResource> exportHistory(@RequestBody PurchaseHistoryCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @Operation(summary = "Gets purchase history data size by criteria")
    @PostMapping("history-data-size")
    public ResponseEntity<Integer> getHistoryDataSize(@RequestBody PurchaseHistoryCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }

    public PurchaseRestAdmin (PurchaseAdminService service, PurchaseConverter converter) {
        super(service, converter);
    }


}