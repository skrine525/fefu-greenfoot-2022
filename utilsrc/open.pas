program pre;
uses System.IO;

function FileExist(name : string) : boolean;
var f: text;
begin
  assign(f, name);
  try
    reset(f);
    close(f);
    result := true;
  except
    result := false;
  end;
end;

var
  n, filecounter: integer;
  files: array of string;
  filename: string;

begin
  files := Directory.GetFiles('.\greenfoot\', '*.greenfoot', SearchOption.TopDirectoryOnly);
  
  filecounter := 1;
  for var i := 0 to length(files)-1 do begin
    filename := files[i].Split('\')[2];
    if(filename <> 'project.greenfoot') then begin
      writeln(filecounter, ': ', filename);
      inc(filecounter);
    end;
  end;
  
  if FileExist('.\greenfoot\project.greenfoot') then begin
    &File.Delete('.\greenfoot\project.greenfoot');
  end;
  
  writeln;
  write('Enter project number: ');
  readln(n);
  
  if not FileExist('.\greenfoot\project' + n + '.greenfoot') then begin
    writeln('project', n, '.greenfoot');
    exit;
  end;
  
  &File.Copy('.\greenfoot\project' + n + '.greenfoot', '.\greenfoot\project.greenfoot');
  writeln('Opening');
  Execute('.\greenfoot\project.greenfoot');
end.