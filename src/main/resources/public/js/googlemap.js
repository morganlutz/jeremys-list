(function() {
  var geocoder;
  var map;
  var address = [];

  function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(-34.397, 150.644);
    var mapOptions = {
      zoom: 3,
      center: latlng
    }
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
  }

  // function codeAddress() {
  //
  //    geocoder.geocode( { 'address': address}, function(results, status) {
  //      if (status == google.maps.GeocoderStatus.OK) {
  //        map.setCenter(results[0].geometry.location);
  //
  //        var infowindow = new google.maps.InfoWindow({
  //          content: contentString
  //        });
  //
  //        var marker = new google.maps.Marker({
  //            map: map,
  //            Title: '' + address,
  //            position: results[0].geometry.location
  //        });
  //
  //        marker.addListener('click', function() {
  //          infowindow.open(map, marker);
  //
  //
  //        })
  //
  //      } else {
  //        alert("Geocode was not successful for the following reason: " + status);
  //      }
  //    });
  //  }

  $(initialize);
 })();
