$(document).ready(function() {
  var table = $('#booksDT').DataTable( {

    columnDefs: [ {
      orderable: false,
      className: 'select-checkbox',
      targets:   0
    } ],
    select: {
      style:    'multi',
      selector: 'td:first-child'
    },
    order: [[ 1, 'asc' ]],
    dom: 'Bfrtip',
    buttons: [
      {
        text: 'Add to cart',
        action: updateCart
      }
    ],

    initComplete: function () {
      // Apply the search
      this.api().columns().every( function () {
          var that = this;

          $( 'input', this.footer() ).on( 'keyup change clear', function () {
              if ( that.search() !== this.value ) {
                  that
                      .search( this.value )
                      .draw();
              }
          } );
      } );
  }

  });
  
  function updateCart() {
    var selectedISBNs = table.cells(['.selected'], 1).data().toArray() //cells(row -> selected class, col -> index 1)
            
    var dataArray = []
    selectedISBNs.forEach(isbn =>
      dataArray.push({"isbn":isbn}))
  
    $.ajax({
      url: "/addToCart",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(dataArray),
  
      success: function(result){
        $("#cart_num").text("(" + result + ")");
      },
    });
  }
});