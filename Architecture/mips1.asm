      .data
n:    .word  12
fibs: .word   0 : n                    
      
      .text
      la   $t0, fibs       
      la   $t5, n        
      lw   $t5, 0($t5)      
      li   $t2, 1           
      add.d $f0, $f2, $f4
      sw   $t2, 0($t0)      
      sw   $t2, 4($t0)      
      addi $t1, $t5, -2     
      
fib:  lw   $t3, 0($t0)       
      lw   $t4, 4($t0)      
      add  $t2, $t3, $t4    
      sw   $t2, 8($t0)     
      addi $t0, $t0, 4      
      addi $t1, $t1, -1     
      bgtz $t1, fib        
		
exit: