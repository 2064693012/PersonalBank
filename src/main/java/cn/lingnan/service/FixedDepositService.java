package cn.lingnan.service;

import cn.lingnan.entity.FixedDeposit;

import java.util.List;

public interface FixedDepositService {
    // Add a new FixedDeposit record
    boolean addFixedDeposit(FixedDeposit fixedDeposit);

    // Update an existing FixedDeposit record
    boolean updateFixedDeposit(int id,String cardID, FixedDeposit fixedDeposit);

    // Get a FixedDeposit record by ID
    FixedDeposit getFixedDepositById(String cardID);

    // Delete a FixedDeposit record by ID
    boolean deleteFixedDeposit(int id,String cardID);

    // Get all FixedDeposit records
    List<FixedDeposit> getAllFixedDeposits();
}
