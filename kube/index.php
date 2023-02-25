<!doctype html>
<html>

<body>
    <h1>PHP kubernates lookup</h1>

    <h2>Hostname: <?php echo gethostname(); ?></h2>

    <h2>Showing environment values:</h2>
    <pre>
<?php

$e = getenv();
foreach ($e as $k => $v) {
    echo "{$k} => {$v}\n";
}


?>
    </pre>

    <h2>Showing content of /etc/podinfo</h2>
    <pre>
<?php
# from https://www.w3schools.com/php/func_directory_readdir.asp
$dir = "/etc/podinfo";

if (is_dir($dir)) {
    if ($dh = opendir($dir)) {
        echo "> START OF /etc/podinfo\n";
        while (($file = readdir($dh)) !== false) {
            $path = "{$dir}/{$file}";
            if (!is_file($path)) {
                continue;
            }
            
            echo "filename: $path \n";
            echo file_get_contents($path);
            echo "\n======\n";
        }

        echo "> END OF /etc/podinfo";
        closedir($dh);
    } else {
        echo "> failed to open /etc/podinfo";
    }
} else {
    echo "> /etc/podinfo is not available, check your pod definition";
}


?>
</pre>
</body>

</html>