#!/usr/bin/perl
use strict;
use warnings;

my $f = "text.txt";

print "Enter process id\n";
my $pid = <STDIN>;
chop $pid;

my $pstree = `pstree -p $pid > text.txt`;

open(my $fh, "<", $f) || die "Couldn't open '".$f."' for reading because: ".$!;

my @arr = ();

print "\n";
while(!eof $fh) {
	my $line = readline $fh;
	print $line;
	while($line =~ m/\D+(\d+)./g) {
		push @arr, $1;
	}
}
print "\n";

for (my $i = $#arr; $i >= 0; $i--) {
    my $kill = `kill -9 $arr[$i]`;
    #print $arr[$i]."\n";
}


close $fh;
