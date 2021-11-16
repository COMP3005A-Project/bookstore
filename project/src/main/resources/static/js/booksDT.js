$(document).ready(function() {
  var table = $('#booksDT').DataTable( {
    columnDefs: [ {
      orderable: false,
      className: 'select-checkbox',
      targets:   0
    } ],
    select: {
      style:    'os',
      selector: 'td:first-child'
    },
    order: [[ 1, 'asc' ]],
    dom: 'Bfrtip',
    buttons: [
      {
        text: 'Add to cart',
        action: updateCart
      }
    ]
  });
  
  function updateCart() {
    var selectedISBNs = table.cells(['.selected'], 1).data().toArray() //cells(row -> selected class, col -> index 1)
            
    var dataArray = []
    selectedISBNs.forEach(isbn =>
      dataArray.push({"isbn":isbn}))
  
    $.ajax({
      url: "/updateCart",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(dataArray),
  
      success: function(result){
        $("#cart_num").text(result);
      },
    });
  }
});