
<?php 

		$connection = mysqli_connect("localhost", "root", "", "tutorial4");

		$sql = "SElect * from student";

		$result = mysqli_query($connection, $sql);

		$xml= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		$xml.= "<students>";
		

		if(mysqli_num_rows($result) > 0 ){
			while ($result_array = mysqli_fetch_assoc($result)) {
				$xml.= "<student>";
					foreach ($result_array as $key => $val) {
						$xml.= "<$key>".$val."</$key>";
					}

				$xml.= "</student>";

				}
		}

		$xml.="</students>";
		header("Content-Type: text/xml");

		echo $xml;

 ?>
