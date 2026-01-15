package com.tk.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrder {
    private String userName;
    private String productName;
    private String productQuantity;
}
