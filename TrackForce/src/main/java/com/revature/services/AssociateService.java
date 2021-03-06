package com.revature.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.revature.criteria.GraphedCriteriaResult;
import com.revature.dao.AssociateDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
/**
 * 
 * @author Adam L. 
 * <p> </p>
 * @version v6.18.06.13
 *
 */
public class AssociateService {
    
    private AssociateDao dao;
    
    // public so it can be used for testing 
    public AssociateService() {dao = new AssociateDaoImpl();};
    public AssociateService(AssociateDao dao) {this.dao = dao;};
    /**
     * @author Adam L. 
     * 
     * <p>Gets the associate given by their associate Id.</p>
     * 
     * @version v6.18.06.13
     * @param associateid
     * @return TfAssociate
     */
    public TfAssociate getAssociate(int associateid) {
        return dao.getAssociate(associateid);
    }
    /**
     * @author Curtis H.
     *
     * <p>Gets the associate given by their associate Id.</p>
     *
     * @version v6.18.06.13
     * @param id
     * @return TfAssociate
     */
    public TfAssociate getAssociateByUserId(int id) {
        return dao.getAssociateByUserId(id);
    }
    
    /**
     * @author Adam L. 
     * 
     * <p>Gets all associates in the database.</p>
     * 
     * @version v6.18.06.13
     * @return List<TfAssociate>
     */
    public List<TfAssociate> getAllAssociates(){
        return dao.getAllAssociates();
    }
    
    public List<TfAssociate> getNAssociates(){
        return dao.getNAssociates();
    }
    
    /**
     * Get a single page of associates filtered by criteria required for the "Associates"
     * page on the front-end. The results can possibly be filtered by marketing status
     * and/or clientId
     * @param startIdx The first result in the query to be returned
     * @param numRes The number of results to return, starting with startIdx
     * @param mktStatus -1 to ignore this field, otherwise return only results matching this marketing status id
     * @param clientId -1 to ignore this field, otherwise return only results matching this client id
     * @return A list of TfAssociate
     * @throws IllegalArgumentException If any arguments are lower than allowed values - min values
     * are 0 for startIdx and numRes, -1 for mktStatus and clientId
     */
    public List<TfAssociate> getAssociatePage(int startIdx, int numRes, int mktStatus, int clientId, String sortText, String firstName, String lastName) {
        if (startIdx < 0 || numRes < 0 || mktStatus < -1 || clientId < -1) {
            LogUtil.logger.error("AssociateService.getAssociatePage() called with bad value");
            throw new IllegalArgumentException();
        }
        
        //Any hibernate exceptions are handled in the resource class
        try {
            return dao.getNAssociateMatchingCriteria(startIdx, numRes, mktStatus, clientId, sortText, firstName, lastName);
        } catch (Exception e) {
        	LogUtil.logger.error("Catch-all Exception Thrown." + e.getMessage());
            throw e;
        }
    }

    /**
     * @author Art B.
     * 
     * pulls in the HashMap of Marketing Status names/counts from the DAO
     */
    public HashMap<String,Integer> getStatusCountsMap() {
    	return dao.getStatusCountsMap();
    }
    

    
    /**
     * @author Adam L. 
     * 
     * <p>Updates an associate based on their Id, which should not change.
     * Any field you wish to update should be included in the TfAsscociate.</p>
     * 
     * <p>Note: if you leave some fields empty in the TfAssociate parameter, 
     *  it will be saved as such!</p>
     * 
     * @version v6.18.06.13
     * @param associate
     * @return true if successful, false otherwise
     */
    public boolean updateAssociatePartial(TfAssociate associate) {
        return dao.updateAssociatePartial(associate);
    }
    public boolean updateAssociate(TfAssociate associate) {
        return dao.updateAssociate(associate);
    }
    public Long getMappedAssociateCountByClientId(Long client_id, Integer mappedStatus) {
        return dao.countMappedAssociatesByValue(
                "TF_CLIENT_ID",
                client_id.toString(),
                mappedStatus);
    }
    /**
     * @author Adam L. 
     * <p>Updates all associates based on their individual Id, which should not change.
     * Any field you wish to update should be included in the TfAsscociate.</p>
     * 
     * <p>Note: if you leave some fields empty in the TfAssociate parameter, 
     *  it will be saved as such!</p>
     * @version v6.18.06.13
     * 
     * @param associates
     * @return
     */
    public boolean updateAssociates(List<TfAssociate> associates) {
        return dao.updateAssociates(associates);
    }
    
    /**
     * @author Adam L. 
     * 
     * <p>Creates an associate in the database.</p>
     * 
     * @since v6.18.06.13
     * @param newassociate
     * @return true if successful, false otherwise
     */
    UserService userService = new UserService();
    public boolean createAssociate(TfAssociate newassociate) {
        try {
            TfUser associateuser = newassociate.getUser();
            associateuser.setPassword(PasswordStorage.createHash(newassociate.getUser().getPassword()));
            newassociate.setUser(associateuser);
			//TODO: Id is not the real id because of auto generate id. Need to add a new update from database to server
        } catch (CannotPerformOperationException e) {
            LogUtil.logger.error(e.getMessage());
        }       
        return dao.createAssociate(newassociate);
    }
    /**
     * @author Curtis H.
     *
     * @since v6.18.06.16
     *
     */
    public List<GraphedCriteriaResult> getMappedInfo(int status) {return dao.getMapped(status);}
    public List<GraphedCriteriaResult> getUndeployed(String which) {return dao.getUndeployed(which);}
    public boolean approveAssociate(int associateId) { return dao.approveAssociate(associateId);}
}