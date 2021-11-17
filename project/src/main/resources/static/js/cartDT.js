$(document).ready(function() {
  var table = $('#cartDT').DataTable( {
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
    searching: false,
    buttons: [
      {
        text: 'Remove from cart',
        className: 'btn btn-danger',
        action: updateCart
      }
    ]
  });
  
  function updateCart() {
    var selectedISBNs = table.cells(['.selected'], 1) //cells(row -> selected class, col -> index 1)
            
    var dataArray = []
    selectedISBNs.data().toArray().forEach(isbn =>
      dataArray.push({"isbn":isbn}));
    
    // If no items selected to remove, don't do anything
    if (dataArray.length == 0) {
      return;
    }

    $.ajax({
      url: "/removeFromCart",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(dataArray),

      success: function(result){
        table.rows(['.selected']).remove().draw();
      },
    });
  }
});