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
        text: 'Remove Selected Books',
        action: removeBooks,
        className: 'btn btn-danger'
      }
    ]
  });
  
  function removeBooks() {
    var selectedISBNs = table.cells(['.selected'], 1).data().toArray() //cells(row -> selected class, col -> index 1)
            
    var dataArray = []
    selectedISBNs.forEach(isbn =>
      dataArray.push({"isbn":isbn}))
  
    $.ajax({
      url: "/removeBooks",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(dataArray),
  
      success: function(){
        table.rows(['.selected']).remove().draw();
      },
    });
  }
});