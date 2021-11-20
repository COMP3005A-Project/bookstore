$(document).ready(function() {
    var placeOrder = document.getElementById("checkout");

    placeOrder.addEventListener("click", () => {
        console.log("Sending request to place order...");

        $.ajax({
            url: "/placeOrder",
            type: "POST",
            contentType: "application/x-www-form-urlencoded",
        
            success: function(result){
              console.log("Successfully created order!");
            },
          });
    });
} );