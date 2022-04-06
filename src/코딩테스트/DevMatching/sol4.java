package 코딩테스트.DevMatching;

/*
SELECT
        cp.CART_ID,
        CASE
        WHEN SUM(cp.price) <c.minimum_requirement THEN 1
        ELSE 0
        END AS ABUSED

        from cart_products cp
        join coupons c on cp.cart_id = c.cart_id
        group by cp.cart_id;
*/